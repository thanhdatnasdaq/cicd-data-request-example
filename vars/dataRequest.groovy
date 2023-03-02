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
                        readExampleFile(exeQueue)
                    }  
                }
            }
        stage("Processing"){
            steps{
                script{
                    parallel(
                        thread1:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread2:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread3:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread4:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread5:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread6:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread7:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread8:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread9:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        },
                        thread10:{
                            if(!exeQueue.isEmpty()){ executeParallel(exeQueue)}
                        }
                    )
                    if(!exeQueue.isEmpty()){ executeHelper(exeQueue)}
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

def readExampleFile(List exeQueue){
    def data = readFile("example.txt").readLines()
    for(line in data){
        exeQueue.add(line)
    }
    print("Complete read file")
}

def executeParallel(List exeQueue){
    while(exeQueue.size > 10){
        print(exeQueue[0])
        exeQueue.remove(0)
    }
}

def executeHelper(List exeQueue){
    while(exeQueue.size > 0){
        print(exeQueue[0])
        exeQueue.remove(0)
    }
}
