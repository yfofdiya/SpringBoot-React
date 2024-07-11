import React, { useState } from 'react'
import { addNewQuery } from '../services/ContactUsService';

const ContactUs = () => {

  const [query, setQuery] = useState({
    name: '',
    mobileNumber: '',
    message: ''
  })

  const handleChange = (e, field) => {
    setQuery({
      ...query,
      [field]: e.target.value
    })
  };

  const resetCustomerQuery = () => {
    setQuery({
      name: '',
      mobileNumber: '',
      message: ''
    })
  }

  const sendCustomerQuery = async (e) => {
    e.preventDefault()
    addNewQuery(query)
      .then(response => {
        console.log(response)
        resetCustomerQuery()
      })
      .catch(error => {
        console.log(error)
      })
  }

  return (
    <div className="min-h-screen p-10 flex justify-center items-center text-white">
      <div className="max-w-4xl mx-auto p-8">
        <h1 className="text-4xl font-bold mb-4 text-center">Contact Us</h1>
        <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
          <div className=''>
            <h2 className="text-2xl font-semibold mb-2">Contact Information</h2>
            <p className="text-lg">Mobile: (+91) 9925004080</p>
            <p className="text-lg">Address: Patel Chok, Dhor Falio, Bhujpur</p>
            <p className="text-lg">Ta.-Mundra, Kutch, Gujarat</p>
            <p className="text-lg mb-2">Pin-370405</p>
            <p className="text-lg">Owner: Prakashbhai Validalbhai Fofdiya</p>
          </div>
          <div>
            <h2 className="text-2xl font-semibold mb-2">Send Us a Message</h2>
            <form onSubmit={sendCustomerQuery}>
              <div className="mb-4">
                <label htmlFor="name" className="block text-lg font-semibold">Name</label>
                <input type="text" id="name" name="name" className="w-full px-4 py-2 rounded-lg border-gray-300 focus:outline-none focus:border-indigo-500 text-black" value={query.name} onChange={e => handleChange(e, "name")} />
              </div>
              <div className="mb-4">
                <label htmlFor="mobileNumber" className="block text-lg font-semibold">Mobile</label>
                <input type="number" id="mobileNumber" name="mobileNumber" className="w-full px-4 py-2 rounded-lg border-gray-300 focus:outline-none focus:border-indigo-500 text-black" value={query.mobileNumber} onChange={e => handleChange(e, "mobileNumber")} />
              </div>
              <div className="mb-4">
                <label htmlFor="message" className="block text-lg font-semibold">Message</label>
                <textarea id="message" name="message" rows="4" className="w-full px-4 py-2 rounded-lg border-gray-300 focus:outline-none focus:border-indigo-500 text-black"
                  value={query.message} onChange={e => handleChange(e, "message")}></textarea>
              </div>
              <div>
                <button type="submit" className="bg-indigo-500 text-white py-2 px-6 rounded-lg hover:bg-indigo-600 transition duration-300">Send Message</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ContactUs
