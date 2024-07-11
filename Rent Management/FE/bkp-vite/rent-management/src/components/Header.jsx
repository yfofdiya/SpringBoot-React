import React, { useState, useEffect } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { doLogout, isLoggedIn } from '../utils/auth';

const Header = () => {

  const [isOpen, setIsOpen] = useState(false);
  const [isLogin, setIsLogin] = useState(false);
  const navigate = useNavigate()

  useEffect(() => {
    let result = isLoggedIn()
    setIsLogin(result)
  });

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const logout = () => {
    doLogout()
    navigate("/login")
  }

  const loginLogoutRegisterLinks = (
    <div className="space-x-4">
      {
        !isLogin && <>
          <Link to="/login" className="text-white">Login</Link>
          <Link to="/register" className="text-white">Register</Link>
        </>
      }
      {
        isLogin && <Link to="/login" className="text-white" onClick={logout}>Logout</Link>
      }
    </div>
  );

  return (
    <header className="bg-red-500 p-4 flex justify-between items-center fixed top-0 left-0 right-0 text-white text-center z-10 w-full">
      <div>
        <div className="md:hidden">
          <button className="text-white" onClick={toggleMenu}>
            {/* Using Tailwind CSS default menu icon */}
            <svg
              className={`w-6 h-6 ${isOpen ? 'hidden' : 'block'}`}
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
              fill="currentColor"
            >
              <path
                fillRule="evenodd"
                d="M3 6a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 10a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1zM3 14a1 1 0 011-1h12a1 1 0 110 2H4a1 1 0 01-1-1z"
                clipRule="evenodd"
              />
            </svg>
            {/* Using Tailwind CSS default close icon */}
            <svg
              className={`w-6 h-6 ${isOpen ? 'block' : 'hidden'}`}
              xmlns="http://www.w3.org/2000/svg"
              viewBox="0 0 20 20"
              fill="currentColor"
            >
              <path
                fillRule="evenodd"
                d="M13.414 6.586a2 2 0 00-2.828 0L10 7.172l-1.586-1.586a2 2 0 10-2.828 2.828L7.172 10l-1.586 1.586a2 2 0 102.828 2.828L10 12.828l1.586 1.586a2 2 0 102.828-2.828L12.828 10l1.586-1.586a2 2 0 000-2.828z"
                clipRule="evenodd"
              />
            </svg>
          </button>
        </div>
        <nav className={`md:flex ${isOpen ? 'flex' : 'hidden'} flex-col md:flex-row md:space-x-4 w-full md:w-auto`}>
          <Link to="/" className="text-white">Home</Link>
          <Link to="/about" className="text-white">About</Link>
          <Link to="/contact" className="text-white">Contact</Link>
        </nav>
      </div>
      <div className="md:flex hidden space-x-4 items-center">
        {loginLogoutRegisterLinks}
      </div>
      <div className="md:hidden">{loginLogoutRegisterLinks}</div>
    </header>
  );
};

export default Header;