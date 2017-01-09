<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script contains helper functions related to certificates management.

----------------------------------------------------------------------------------------------------------------------------------#>

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
    $store.Open([System.Security.Cryptography.X509Certificates.OpenFlags]::ReadWrite)
    $store.Add($x509);
    $store.Close();
}