import 'package:flutter/material.dart';

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
                title: 'To-Do List App',
                theme: ThemeData(
                primarySwatch: Colors.blue,
      ),
        home: TodoListPage(),
    );
    }
}

class TodoListPage extends StatefulWidget {
    @override
    _TodoListPageState createState() => _TodoListPageState();
}

class _TodoListPageState extends State<TodoListPage> {
    // List to hold the tasks
    List<String> tasks = [];

    // Controller to manage the input field
    TextEditingController taskController = TextEditingController();

    // Function to add a new task
    void _addTask() {
        if (taskController.text.isNotEmpty) {
            setState(() {
                tasks.add(taskController.text);
                taskController.clear(); // Clear the input field
            });
        }
    }

    // Function to delete a task
    void _deleteTask(int index) {
        setState(() {
            tasks.removeAt(index); // Remove task at the given index
        });
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
                appBar: AppBar(
                title: Text('To-Do List'),
      ),
        body: Column(
                children: [
        // Input field for adding tasks
        Padding(
                padding: const EdgeInsets.all(16.0),
                child: TextField(
                controller: taskController,
                decoration: InputDecoration(
                labelText: 'Enter a new task',
                border: OutlineInputBorder(),
              ),
            ),
          ),
        // Button to add task
        ElevatedButton(
                onPressed: _addTask,
                child: Text('Add Task'),
          ),
        // List of tasks
        Expanded(
                child: ListView.builder(
                itemCount: tasks.length,
                itemBuilder: (context, index) {
            return ListTile(
                    title: Text(tasks[index]),
                    trailing: IconButton(
                    icon: Icon(Icons.delete),
                    onPressed: () => _deleteTask(index),
                  ),
            onTap: () {
                // Edit task functionality can go here
                _editTask(index);
            },
                );
        },
            ),
          ),
        ],
      ),
    );
    }

    // Function to edit a task
    void _editTask(int index) {
        taskController.text = tasks[index]; // Populate input field with the current task
        showDialog(
                context: context,
                builder: (context) {
        return AlertDialog(
                title: Text('Edit Task'),
                content: TextField(
                controller: taskController,
                decoration: InputDecoration(
                labelText: 'Edit task',
            ),
          ),
        actions: [
        TextButton(
                onPressed: () {
            setState(() {
                tasks[index] = taskController.text;
            });
            Navigator.pop(context); // Close the dialog
        },
        child: Text('Save'),
            ),
        TextButton(
                onPressed: () {
            Navigator.pop(context); // Close the dialog without saving
        },
        child: Text('Cancel'),
            ),
          ],
        );
      },
    );
    }
}
