import React, { useEffect, useState } from 'react'
import { createEmployee, employeeById, updateEmployee } from '../services/EmployeeService'
import { useNavigate, useParams } from 'react-router-dom'

const Employee = ({ title }) => {

    const [firstName, setFirstName] = useState('')
    const [lastName, setLastName] = useState('')
    const [email, setEmail] = useState('')

    const [errors, setErrors] = useState({
        firstName: '',
        lastName: '',
        email: ''
    })

    const { id } = useParams()

    useEffect(() => {
        if (id) {
            employeeById(id)
                .then(response => {
                    setFirstName(response.data.data.firstName)
                    setLastName(response.data.data.lastName)
                    setEmail(response.data.data.email)
                })
                .catch(error => console.log(error))
        }
    }, [id])

    const navigator = useNavigate()

    const validateForm = () => {
        let isValid = true;

        const errorsCopy = { ...errors }

        if (firstName.trim()) {
            errorsCopy.firstName = ''
        } else {
            errorsCopy.firstName = 'First name is required'
            isValid = false;
        }
        if (lastName.trim()) {
            errorsCopy.lastName = ''
        } else {
            errorsCopy.lastName = 'Last name is required'
            isValid = false;
        }
        if (email.trim()) {
            errorsCopy.email = ''
        } else {
            errorsCopy.email = 'Email is required'
            isValid = false;
        }

        setErrors(errorsCopy)
        return isValid
    }

    const addOrUpdateEmployeeData = (event) => {
        event.preventDefault()

        const employee = { firstName, lastName, email }

        if (validateForm()) {
            if (id) {
                updateEmployee(id, employee)
                    .then(response => {
                        console.log(response.data)
                        navigator('/employees')
                    })
                    .catch(error => console.log(error))
            } else {
                createEmployee(employee)
                    .then(response => {
                        console.log(response.data)
                        navigator('/employees')
                    })
                    .catch(error => console.log(error))
            }
        }
    }

    return (
        <div className="max-w-md mx-auto bg-white p-8 rounded-md shadow-md">
            <h2 className="text-4xl font-semibold mb-4 text-center">{title} Employee</h2>
            <form>
                <div className="mb-4">
                    <label htmlFor="firstName" className="block text-gray-700 text-sm font-bold mb-2">First Name</label>
                    <input id="firstName" type="text" placeholder="Enter your first name" className="border rounded-md px-3 py-2 w-full focus:outline-double" value={firstName} onChange={(event) => setFirstName(event.target.value)} />
                    {
                        errors.firstName && <p className="text-red-500">{errors.firstName}</p>
                    }
                </div>
                <div className="mb-4">
                    <label htmlFor="lastName" className="block text-gray-700 text-sm font-bold mb-2">Last Name</label>
                    <input id="lastName" type="text" placeholder="Enter your last name" className="border rounded-md px-3 py-2 w-full focus:outline-double" value={lastName} onChange={(event) => setLastName(event.target.value)} />
                    {
                        errors.lastName && <p className="text-red-500">{errors.lastName}</p>
                    }
                </div>
                <div className="mb-4">
                    <label htmlFor="email" className="block text-gray-700 text-sm font-bold mb-2">Email</label>
                    <input id="email" type="email" placeholder="Enter your email" className="border rounded-md px-3 py-2 w-full focus:outline-double" value={email} onChange={(event) => setEmail(event.target.value)} />
                    {
                        errors.email && <p className="text-red-500">{errors.email}</p>
                    }
                </div>
                <button type="submit" className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded w-full" onClick={addOrUpdateEmployeeData}>{title}</button>
            </form>
        </div>
    )
}

export default Employee
