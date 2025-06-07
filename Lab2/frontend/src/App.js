import { useEffect, useState } from "react";
import axios from "axios";

function App() {
  const [tasks, setTasks] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:3001/tasks")
      .then(res => setTasks(res.data))
      .catch(err => console.error(err));
  }, []);
  const [newTitle, setNewTitle] = useState('');
const [newDescription, setNewDescription] = useState('');

  return (
    <div style={{ padding: "20px" }}>
      <h1>Task List</h1>
      <h2>Додати нову задачу</h2>
        <form onSubmit={(e) => {
          e.preventDefault();
          axios.post("http://localhost:3001/tasks", {
            title: newTitle,
            description: newDescription
          }).then(res => {
            setTasks(prev => [...prev, res.data]);
            setNewTitle('');
            setNewDescription('');
          });
        }}>
          <input
            type="text"
            placeholder="Назва задачі"
            value={newTitle}
            onChange={e => setNewTitle(e.target.value)}
            required
          />
          <br />
          <textarea
            placeholder="Опис"
            value={newDescription}
            onChange={e => setNewDescription(e.target.value)}
            required
          />
          <br />
          <button type="submit">Додати задачу</button>
        </form>

      <ul>
        {tasks.map(task => (
          <li key={task.id}>
            <strong>{task.title}</strong><br />
            <em>{task.description}</em><br />
            <button onClick={() => {
              axios.delete(`http://localhost:3001/tasks/${task.id}`)
                .then(() => {
                  setTasks(tasks.filter(t => t.id !== task.id));
                });
            }}>Видалити</button>
          </li>

        ))}
      </ul>
    </div>
  );
}

export default App;
