<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script contains helper functions related to certificates management.

----------------------------------------------------------------------------------------------------------------------------------#>

# -----------------------------------------------------------------------------------------------------------------------
# Imports certificate is not already installed, to specified store and location with specified storage flags.
# -----------------------------------------------------------------------------------------------------------------------
function Import-CertificateWithCheck($certificatePath,
    [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags] $keyStorageFlags,   # specifies storageFlags to use (eg. Current User/LocalMachine)
    [System.Security.Cryptography.X509Certificates.StoreName] $storeName,                   # specifies name of the store to import to.
    [System.Security.Cryptography.X509Certificates.StoreLocation] $storeLocation            # specifies location where to import the cert.
) {
    "Reading subject name from certificate ..."
    $subjectNm = Get-SubjectName -certificatePath $certificatePath -keyStorageFlags $keyStorageFlags
    $thumbprint = Get-Thumbprint -certificatePath $certificatePath -keyStorageFlags $keyStorageFlags
    "Subject name=[$subjectNm]; Thumbprint=[$thumbprint]"
    "Checking if certificate with thumbprint=[$thumbprint] is already installed ..."
    $findType = [System.Security.Cryptography.X509Certificates.X509FindType]::FindByThumbprint
    $certFound = Find-Certificate -findIdentifier "$thumbprint" -storeName $storeName -storeLocation $storeLocation -findType $findType
    "Certificate CHECK STATUS. Found='$certFound'"
    if ($certFound -eq $false) {
        "Importing certificate ..."
        Import-Certificate -certificatePath "$certificatePath" -keyStorageFlags $keyStorageFlags -storeName $storeName -storeLocation $storeLocation
        "Certificate imported."
    }
}


# -----------------------------------------------------------------------------------------------------------------------
# Imports specified certificate to specified store and location with the specified storage flags.
# 
# SAMPLE USAGE: 
#   $storageFlagsEnum = [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags]::MachineKeySet
#   $storeNameEnum = [System.Security.Cryptography.X509Certificates.StoreName]::My
#   $storeLocationEnum = [System.Security.Cryptography.X509Certificates.StoreLocation]::LocalMachine
#   Import-Certificate -certificatePath "C:\temp\autoanalyzererts\autotestanalyzer001.surveyordev.com.pfx" -keyStorageFlags $storageFlagsEnum -storeName $storeNameEnum -storeLocation $storeLocationEnum
# -----------------------------------------------------------------------------------------------------------------------
function Import-Certificate($certificatePath,
    [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags] $keyStorageFlags,   # specifies storageFlags to use (eg. Current User/LocalMachine)
    [System.Security.Cryptography.X509Certificates.StoreName] $storeName,                   # specifies name of the store to import to.
    [System.Security.Cryptography.X509Certificates.StoreLocation] $storeLocation            # specifies location where to import the cert.
) {
    $x509 = New-Object System.Security.Cryptography.X509Certificates.X509Certificate2
    $x509.Import($certificatePath, "", $keyStorageFlags)
    $store = New-Object System.Security.Cryptography.X509Certificates.X509Store($storeName, $storeLocation)
    "Opening certificate store for importing ..."
    $store.Open([System.Security.Cryptography.X509Certificates.OpenFlags]::ReadWrite)
    "Adding certificate to store ..."
    $store.Add($x509);
    $store.Close();
}


# -----------------------------------------------------------------------------------------------------------------------
# Returns subject name from the certificate.
# -----------------------------------------------------------------------------------------------------------------------
function Get-SubjectName($certificatePath,
    [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags] $keyStorageFlags
) {
    $x509 = New-Object System.Security.Cryptography.X509Certificates.X509Certificate2
    $x509.Import($certificatePath, "", $keyStorageFlags)
    $x509.Subject
}


# -----------------------------------------------------------------------------------------------------------------------
# Returns thumbprint from the certificate.
# -----------------------------------------------------------------------------------------------------------------------
function Get-Thumbprint($certificatePath,
    [System.Security.Cryptography.X509Certificates.X509KeyStorageFlags] $keyStorageFlags
) {
    $x509 = New-Object System.Security.Cryptography.X509Certificates.X509Certificate2
    $x509.Import($certificatePath, "", $keyStorageFlags)
    $x509.Thumbprint
}


# -----------------------------------------------------------------------------------------------------------------------
# Returns whether a certificate was found in specified cert store and location.
# 
# SAMPLE USAGE: 
#   $subjectName = "simauto-analyzer1"
#   $domainNameSuffix = ".surveyordev.com"
#   $storeNameEnum = [System.Security.Cryptography.X509Certificates.StoreName]::My
#   $storeLocationEnum = [System.Security.Cryptography.X509Certificates.StoreLocation]::LocalMachine
#   $foundCert = Find-Certificate -subjectName "$subjectName" -domainNameSuffix $domainNameSuffix -storeName $storeNameEnum -storeLocation $storeLocationEnum
#   "Got certificate = '$foundCert'"
# -----------------------------------------------------------------------------------------------------------------------
function Find-Certificate($subjectName, $domainNameSuffix,
    [System.Security.Cryptography.X509Certificates.StoreName] $storeName,                   # specifies name of the store to import to.
    [System.Security.Cryptography.X509Certificates.StoreLocation] $storeLocation            # specifies location where to import the cert.    
) {
    $retVal = $false
    $findType = [System.Security.Cryptography.X509Certificates.X509FindType]::FindBySubjectName
    $findIdentifier = $subjectName + $domainNameSuffix
    $retVal = Find-Certificate -findIdentifier "$findIdentifier" -storeName $storeNameEnum -storeLocation $storeLocationEnum -findType $findType
    $retVal
}


# -----------------------------------------------------------------------------------------------------------------------
# Returns whether a certificate was found in specified cert store and location using specified FindType.
# -----------------------------------------------------------------------------------------------------------------------
function Find-Certificate($findIdentifier,
    [System.Security.Cryptography.X509Certificates.StoreName] $storeName,                   # specifies name of the store to import to.
    [System.Security.Cryptography.X509Certificates.StoreLocation] $storeLocation,            # specifies location where to import the cert.    
    [System.Security.Cryptography.X509Certificates.X509FindType] $findType
) {
    $retVal = $false
    $store = New-Object System.Security.Cryptography.X509Certificates.X509Store($storeName, $storeLocation)
    $store.Open([System.Security.Cryptography.X509Certificates.OpenFlags]::ReadOnly)
    $coll = $store.Certificates.Find($findType, $findIdentifier, $false)
    if ($coll.Count -gt 0) {
        $retVal = $true
    }
    $retVal
}
