folder('github') {
    displayName('github')
}

folder('github/valengus') {
    displayName('valengus')
}

folder('github/valengus/docker') {
    displayName('docker')
}

List filesPath=[]
File fileDir=new File(".")
fileDir.eachDirRecurse() { 
  dir -> dir.eachFileMatch(~/.*.pkr*hcl/) { 
    file -> filesPath.add(file.path)
    println(file)
  }  
}