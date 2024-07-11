import Employee from "./components/Employee"
import ListEmployee from "./components/ListEmployee"
import { BrowserRouter, Route, Routes } from "react-router-dom"

function App() {

  return (
    <BrowserRouter>
      <Routes>
        {/* http://localhost:3000/ */}
        <Route path="/" element={<ListEmployee />} />

        {/* http://localhost:3000/employees */}
        <Route path="/employees" element={<ListEmployee />} />

        {/* http://localhost:3000/add-employee */}
        <Route path="/add-employee" element={<Employee title="Add" />} />

        {/* http://localhost:3000/edit-employee/1 */}
        <Route path="/edit-employee/:id" element={<Employee title="Update" />} />
      </Routes>

    </BrowserRouter>
  )
}

export default App
