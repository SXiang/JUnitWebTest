**Instructions for running BackPack Simulator using setup scripts**

1. Execute following command to install BackPack server simulator pre-requisites:

```
setup-simulator.cmd '<host_repo_folder>'
```

where,
<host_repo_folder> = path to tools repository (for eg. C:\Repositories\host in dev machine, or C:\BuildAgent\work\host in CI)

2. Start BackPack server simulator by using either one of the two options:

OPTION a:

```
cd <host_repo_folder>\src\main\python\Host\Utilities\BackpackServer
SET BUILDROOT='<repo_root_folder>'
start-sim.bat
```

where,
<repo_root_folder> = path to repository root folder (for eg. C:\Repositories in dev machine, or C:\BuildAgent\work in CI)
<host_repo_folder> = path to tools repository (for eg. C:\Repositories\host in dev machine, or C:\BuildAgent\work\host in CI)

OPTION b:

```
start-simulator.cmd '<repo_root_folder>'
```

where,
<repo_root_folder> = path to repository root folder (for eg. C:\Repositories in dev machine, or C:\BuildAgent\work in CI)
