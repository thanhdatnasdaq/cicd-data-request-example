def call(projectName, requiredAgent='java8'){
def exeQueue = []
pipeline{
    agent any
    
    stages{
        stage("Set Up"){
            steps{
                    echo "seting up ..."
                    echo "Java Home: $JAVA_HOME"
                    sh 'java -version'
                }
            }
        stage("Build"){
            steps{
                    echo "building ..."
                    sh "whoami"
                    sh "pwd"
                    sh "env"
                    echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
                    script{                        
                        readExampleFile()
                    }  
                }
            }
        stage("Processing"){
            steps{
                script{
                    parallel(
                        thread1:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread2:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread3:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread4:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread5:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread6:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread7:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread8:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread9:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        },
                        thread10:{
                            if(!exeQueue.isEmpty()){ executeParallel()}
                        }
                    )
                    if(!exeQueue.isEmpty()){ executeHelper}
                }
            }
        }
        stage("Deploy"){
            steps{
                echo "deploying ..."
                echo "confirmDeploy: ${params.confirmDeploy}"
                }
            }
        }
    }
}

def readExampleFile(){
    def data = readFile("example.txt").readLines()
    for(line in data){
        exeQueue.add(line)
    }
}

def executeParallel(){
    while(exeQueue.size > 10){
        print(exeQueue[0])
        exeQueue.remove(0)
    }
}

def executeHelper(){
    while(exeQueue.size > 0){
        print(exeQueue[0])
        exeQueue.remove(0)
    }
}
