pipeline {
    agent any

    tools {
        // Install the Maven version configured as "M3" and add it to the path.
        maven "M2"
    }
//     triggers {
//         cron('0 8 * * *')
//     }
    parameters {
        gitParameter ( branch: '',
        branchFilter: 'origin/(.*)',
        defaultValue: 'master',
        description: '',
        name: 'BRANCH',
        quickFilterEnabled: true,
        selectedValue: 'NONE',
        sortMode: 'NONE',
        tagFilter: '*',
        type: 'PT_BRANCH' )
    }

    stages {
//        stage('Prepare Selenoid: starting containers') {
//            steps {
//                sh "docker pull selenoid/$BROWSER"
//                sh 'chmod +x /Volumes/Work/QaseIO/src/test/resources/ConfigurationManager/cm'
//                sh '/Volumes/Work/QaseIO/src/test/resources/ConfigurationManager/cm selenoid start'
//                sh '/Volumes/Work/QaseIO/src/test/resources/ConfigurationManager/cm selenoid-ui start'
//                sh '/Volumes/Work/QaseIO/src/test/resources/ConfigurationManager/cm selenoid status'
//                sh 'curl http://localhost:4444/status'
//            }
//        }

            stage('Launching android emulator'){
                steps{
					script {
						try {
						// Launching android emulator
                        bat 'C:/Users/Selecty/AppData/Local/Android/Sdk/emulator'
                        bat './emulator -avd Pixel_5_API_29'
                        } catch (Exception error) {
                        unstable('Can not launch emulator...')
						}
					}
				}
			}
        stage('UI tests') {

            steps {

                script {

                    try {

                        // Get some code from a GitHub repository
                        git branch: "${params.BRANCH}",  url: 'https://github.com/Maxim-Kazliakouski/AndroidMavenProject.git'

                      //  withCredentials ([
                      //      string(credentialsId: 'qase_token',
                      //  variable: 'TOKEN_CREDENTIALS'),
                      //      string(
                      //          credentialsId: 'qase_password',
                      //          variable: 'PASSWORD_CREDENTIALS')
                      //  ]) {

                            // Run Maven on a Unix agent.
                            // sh "mvn clean -Dsurefire.suiteXmlFiles=src/test/resources/chromeLaunchTest.xml \
                            // To run Maven on a Windows agent, use
                            bat "mvn clean '-Dsurefire.suiteXmlFiles=src/test/resources/launchTest.xml' test"
            //    -P UI -Dbrowser=$BROWSER \
            //    -DbrowserVersion=$VERSION \
            //   -DvideoTestRecord=$VIDEO_TEST_RECORD \
            //    -Dheadless=$HEADLESS \
            //    -Dqase.username=$USERNAME \
            //    -Dqase.password=$PASSWORD_CREDENTIALS \
            //    -Dtoken=$TOKEN_CREDENTIALS \
            //    -DtestRun=$TESTRUN \
            //    -DcodeProject=$CODEPROJECT test"
                        }
                    } catch (Exception error) {
                        unstable('Testing failed')
                    }
                }
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Generating Allure report') {
            steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}