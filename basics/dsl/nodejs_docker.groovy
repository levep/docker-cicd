job('NodeJS Docker example') {
    scm {
        git('https://github.com/levep/docker-cicd.git','master') 
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('levep79/amdocsapp')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('lev-dockerhub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

