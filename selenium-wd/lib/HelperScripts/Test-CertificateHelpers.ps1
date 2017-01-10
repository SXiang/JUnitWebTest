. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CertificateHelpers.ps1

### Method Tests: 
$executeTests = $true
$testsToRun = "Test-01","Test-02"

if ($executeTests) {
    if ($testsToRun.Contains("Test-01")) {
        #### [Test-01]: PRINT SUBJECT NAME & THUMBPRINT FROM ALL CERTS IN A FOLDER.
        $certFolder = "C:\Repositories\surveyor-qa\selenium-wd\data\certs"
        $FILTERS = "*.pfx","*.pem"
        $FILTERS | % {
            $filter = $_
            Get-ChildItem $certFolder -Filter "$filter" -Recurse | % {
                $file = $_
                $filename = $file.Name
                $certFilePath = $file.FullName

                $storageFlagsEnum = [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags]::MachineKeySet
                $subjectNm = Get-SubjectName -certificatePath "$certFilePath" -keyStorageFlags $storageFlagsEnum
                if ($subjectNm -ne $NULL -and $subjectNm -ne "") {
                    $subjectNm = $subjectNm.Replace("CN=", "")
                }
                $thumbprint = Get-Thumbprint -certificatePath "$certFilePath" -keyStorageFlags $storageFlagsEnum
                "Cert=[$filename]; SubjectName=[$subjectNm]; Thumbprint=[$thumbprint]"
            }
        }
    }

    if ($testsToRun.Contains("Test-02")) {
        #### [Test-02]: CHECK AND IMPORT CERTIFICATE.
        $certFolder = "C:\Repositories\surveyor-qa\selenium-wd\data\certs"
        $storageFlagsEnum = [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags]::MachineKeySet
        $storeNameEnum = [System.Security.Cryptography.X509Certificates.StoreName]::My
        $storeLocationEnum = [System.Security.Cryptography.X509Certificates.StoreLocation]::LocalMachine
        $FILTERS = "*.pfx"
        $FILTERS | % {
            $filter = $_
            Get-ChildItem $certFolder -Filter "$filter" -Recurse | % {
                $file = $_
                $filename = $file.Name
                $certFilePath = $file.FullName

                Import-CertificateWithCheck -certificatePath $certFilePath -keyStorageFlags $storageFlagsEnum -storeName $storeNameEnum -storeLocation $storeLocationEnum
            }
        }
    }
}