import os

def envPathExists(ps):
    paths = os.environ['PATH']
    pArr = paths.split(';')
    for p in pArr:
        if (p.lower() == ps.lower()):
            return True
    return False
