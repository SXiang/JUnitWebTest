package common.source;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

public class S3Interface {
	private static final Integer BYTE_LEN = 1024;
	private final static String DEFAULT_BUCKET = "picarro-p3sqa";
	private AmazonS3 s3Client = null;

	public final static String S3_BUCKET_BASE_URL = "https://" + DEFAULT_BUCKET + ".amazonaws.com";

	private S3Interface() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(TestContext.INSTANCE.getTestSetup().getAwsAccessKeyId(),
				TestContext.INSTANCE.getTestSetup().getAwsSecretKeyId());
		s3Client = AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .withRegion(Regions.US_WEST_1)
                        .build();
	}

	public static S3Interface newInterface() {
		return new S3Interface();
	}

	public static class S3Folder {
		private String name;

		private S3Folder(String name) {
			this.name = name;
		}

		public static S3Folder fromUri(URI uri) {
			return new S3Folder(uri.getHost());
		}

		@Override
		public String toString() {
			return name;
		}
	}

	public static class S3File {
		private S3Folder folder;
		private Path filePath;

		private S3File(S3Folder folder, Path filePath) {
			this.folder = folder;
			this.filePath = filePath;
		}

		public static S3File fromFileAndUri(Path fileFullPath, URI baseUri) {
			return new S3File(S3Folder.fromUri(baseUri), fileFullPath);
		}

		public Path getFilePath() {
			return filePath;
		}

		@Override
		public String toString() {
			return String.format("%s/%s", folder.toString(), filePath.getFileName().toString());
		}

		public String toString(String prefix) {
			return String.format("%s/%s_%s", folder.toString(), prefix, filePath.getFileName().toString());
		}
	}

	public void deleteFile(S3File s3File) throws AmazonServiceException {
		Log.method("deleteFile", s3File);
		deleteFile(s3File, DEFAULT_BUCKET);
	}

	public void downloadFile(S3File s3File, String outputDirectory) throws IOException {
		Log.method("downloadFile", s3File, outputDirectory);
		downloadFile(s3File, outputDirectory, DEFAULT_BUCKET);
	}

	public String uploadFile(S3File s3File) {
		Log.method("uploadFile", s3File);
		return uploadFile(s3File, DEFAULT_BUCKET);
	}

	private void deleteFile(S3File s3File, String bucketName) throws AmazonServiceException {
		try {
			s3Client.deleteObject(bucketName, s3File.toString());
		} catch (AmazonServiceException e) {
		    Log.error("AmazonServiceException | " + e.getErrorMessage());
		    throw e;
		}
	}

	private Bucket createBucket(String bucketName) throws AmazonS3Exception {
		Bucket bucket = null;
		if (s3Client.doesBucketExist(bucketName)) {
		    System.out.format("Bucket %s already exists!\n", bucketName);
		    bucket = getBucket(bucketName);
		} else {
		    try {
		        bucket = s3Client.createBucket(bucketName);
		    } catch (AmazonS3Exception ex) {
		    	Log.error("AmazonServiceException | " + ex.getErrorMessage());
		    	throw ex;
		    }
		}

		return bucket;
	}

	private void downloadFile(S3File s3File, String outputDirectory, String bucketName) throws IOException {
		try {
			String keyName = s3File.toString();
			String outFileName = s3File.getFilePath().getFileName().toString();
		    S3Object s3Obj = s3Client.getObject(bucketName, keyName);
		    try (S3ObjectInputStream s3IStream = s3Obj.getObjectContent()) {
			    try (FileOutputStream fStream = new FileOutputStream(new File(Paths.get(outputDirectory, outFileName).toString()))) {
				    byte[] readBuffer = new byte[BYTE_LEN];
				    int readLen = 0;
				    while ((readLen = s3IStream.read(readBuffer)) > 0) {
				        fStream.write(readBuffer, 0, readLen);
				    }
			    }
		    }
		} catch (AmazonServiceException e) {
		    Log.error("AmazonServiceException | " + e.getErrorMessage());
		    throw e;
		} catch (FileNotFoundException e) {
			Log.error("FileNotFoundException | " + e.getMessage());
		    throw e;
		} catch (IOException e) {
			Log.error("IOException | " + e.getMessage());
		    throw e;
		}
	}

	private void ensureBucketExists(String bucketName) {
		Bucket bucket = getBucket(bucketName);
		if (bucket == null) {
			bucket = createBucket(bucketName);
		}
	}

	private Bucket getBucket(String bucketName) {
        Bucket namedBucket = null;
        List<Bucket> buckets = s3Client.listBuckets();
        for (Bucket b : buckets) {
            if (b.getName().equals(bucketName)) {
                namedBucket = b;
            }
        }

        return namedBucket;
    }

    private String uploadFile(S3File s3File, String bucketName) throws AmazonServiceException {
		ensureBucketExists(bucketName);
		String keyName = "";
		try {
			Path filePath = s3File.getFilePath();
			String fileFullPath = filePath.toString();
			keyName = s3File.toString(TestSetup.getUUIDString());
			s3Client.putObject(new PutObjectRequest(bucketName, keyName, new File(fileFullPath)));
		} catch (AmazonServiceException ex) {
			Log.error("AmazonServiceException | " + ex.getErrorMessage());
			throw ex;
		}

		return keyName;
	}
}