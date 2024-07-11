import React, { useEffect, useState } from 'react'
import { deleteById, listAllEmployees } from '../services/EmployeeService'
import { useNavigate } from 'react-router-dom'

const ListEmployee = () => {
    const [allEmployees, setAllEmployees] = useState([])
    const navigator = useNavigate()

    useEffect(() => {
        getAllEmployees()
    }, [])

    const getAllEmployees = () => {
        listAllEmployees()
            .then((response) => setAllEmployees(response.data.data))
            .catch(error => console.error(error))
    }

    const addNewEmployee = () => {
        navigator('/add-employee')
    }

    const updateEmployee = (id) => {
        navigator(`/edit-employee/${id}`)
    }

    const deleteEmployee = (id) => {
        deleteById(id)
        .then((response) => {
            console.log(response.data)
            getAllEmployees()
        })
        .catch(error => console.log(error))
    }

    return (
        <div className="container mx-auto py-6">
            <h2 className="text-4xl font-semibold mb-4 text-center">List of Employees</h2>
            <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mb-2 rounded" onClick={addNewEmployee}>
                Add Employee
            </button>
            <div className="overflow-x-auto">
                <table className="table-auto w-full">
                    <thead>
                        <tr className="bg-gray-800 text-stone-100">
                            <th className="px-4 py-2 text-left">Id</th>
                            <th className="px-4 py-2 text-left">First Name</th>
                            <th className="px-4 py-2 text-left">Last Name</th>
                            <th className="px-4 py-2 text-left">Email</th>
                            <th className="px-4 py-2 text-left">Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            allEmployees.map((emp, index) =>
                                <tr key={emp.id} className={index % 2 !== 0 ? 'bg-gray-300' : ''}>
                                    <td className="border px-4 py-2">{emp.id}</td>
                                    <td className="border px-4 py-2">{emp.firstName}</td>
                                    <td className="border px-4 py-2">{emp.lastName}</td>
                                    <td className="border px-4 py-2">{emp.email}</td>
                                    <td className="border px-4 py-2"><button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mb-2 rounded" onClick={() => updateEmployee(emp.id)}>
                                        Update
                                    </button>
                                    <button className="bg-red-500 hover:bg-red-700 text-white font-bold ml-2 py-2 px-4 mb-2 rounded" onClick={() => deleteEmployee(emp.id)}>
                                        Delete
                                    </button></td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>
            </div>
        </div>

    )
}

export default ListEmployee
