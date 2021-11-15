Feature: Thinkific Test cases

    @Smoke @CreateExcel, @WriteExcel
    Scenario: UI task
        Given User logged into the application
        When Navigate to 'Instructors' section and create new instructor
        Then Verify Successfully Created Message on the left bottom side

    @Smoke @ReadExcel, @DeleteExcel
    Scenario: API task
        Given User successfully hit the API and response code is "200"
        Then Verify Instructor created in UI task is in response
        Then Verify response of API contains data of Instructor created in UI task
