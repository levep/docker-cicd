job('NodeJS Docker example') {
    scm {
        git('https://github.com/levep/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL lev')
            node / gitConfigEmail('jenkins-dsl@lev.academy')
        } 
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

