import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import ImageSlider from '../components/ImageSlider';
import { isLoggedIn } from '../utils/auth'

const Home = () => {

  const [isLogin, setIsLogin] = useState("");

  useEffect(() => {
    let result = isLoggedIn()
    setIsLogin(result)
  }, [])

  return (
    <div className="flex flex-col h-screen text-white">
      <main>
        <div className="text-center m-16">
          <h1 className="text-4xl font-bold mb-4">Welcome to Fofdiya's Home Rental</h1>
          <p className="text-lg mb-8">Your Home Away from Home</p>
          <button className="bg-blue-500 hover:bg-blue-600 text-white font-bold py-2 px-4 rounded-lg">
            {
              !isLogin && <Link to="/login">Get Started</Link>
            }
            {
              isLogin && <Link to='/all/houses'>Get Started</Link>
            }
          </button>
        </div>
        <div>
          <ImageSlider />
        </div>
        <div className="text-center m-16 text-2xl">
          Call on <b>(+91) 9925004080</b> for inquiry
        </div>
      </main>
    </div>
  )
}

export default Home
