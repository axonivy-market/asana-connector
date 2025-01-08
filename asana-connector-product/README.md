# Asana Connector

[Asana](https://asana.com/) is a web and mobile application designed for team collaboration and project management.
It allows users to organize tasks, track progress, assign work, set deadlines, and communicate within teams, making project and workflow management efficient.
This connector provides the following capabilities:
- Create a task.
- Get task details.
- Update task details.
- Delete task.

## Demo

Check out the demo implementations we have prepared for the Asana connector:

![demo-process](demo-process.png).

This demo includes two main processes:
- getTaskDetails: Validates the input task ID and redirects to the task details screen, where update and delete actions can be performed.
- createTask: Creates a new task with sample data (e.g., task name, workspace, assignee, and due date).

## Setup

In order to use this product you must configure the variables.

Add the following code block to your `config/variables.yaml` file of your main Business Project that will utilize this product:

```
# yaml-language-server: $schema=https://json-schema.axonivy.com/app/12.0.0/variables.json
Variables:
  asana:
    # The PAT key that can be created in https://app.asana.com/0/my-apps.
    accessToken: ''
```
### Asana Registration

1. Register for an Asana account on the [Asana Dashboard](https://asana.com/)
1. Once logged in, [Create a PAT](https://app.asana.com/0/my-apps)
(Personal access token), which you will later add to the `variables.yml`.
    ![create-pat](create-pat.png).
1. Refer to the [Quick start guide](https://developers.asana.com/docs/quick-start) to learn how to access your Workspace GID.
