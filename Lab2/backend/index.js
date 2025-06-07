const express = require('express');
const cors = require('cors');
const app = express();
app.use(cors());
app.use(express.json());

app.post('/tasks', (req, res) => {
  const newTask = {
    id: tasks.length + 1,
    title: req.body.title,
    description: req.body.description
  };
  tasks.push(newTask);
  res.status(201).json(newTask);
});
// Видалити задачу за ID
app.delete('/tasks/:id', (req, res) => {
  const id = parseInt(req.params.id);
  const index = tasks.findIndex(task => task.id === id);
  if (index !== -1) {
    tasks.splice(index, 1);
    res.status(200).json({ message: 'Task deleted' });
  } else {
    res.status(404).json({ message: 'Task not found' });
  }
});


let tasks = [
  { id: 1, title: "Написати звіт", description: "Оформити лабораторну роботу №2" },
  { id: 2, title: "Протестувати додаток", description: "Перевірити GET-запит до /tasks" },
  { id: 3, title: "Підготувати звіт", description: "Написати звіт у форматі Docx" }
];
app.get('/tasks', (req, res) => {
  res.json(tasks);
});

app.listen(3001, () => console.log('Server running on http://localhost:3001'));
