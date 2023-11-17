import { BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Project from "./pages/Project"
import Projects from "./pages/Projects"

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/Project" element={<Project />}></Route>
        <Route path="/" element={<Projects />}></Route>
      </Routes>
    </Router>
  );
}

export default App;
