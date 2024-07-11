import { BrowserRouter, Route, Routes } from "react-router-dom"
import Home from "./pages/Home"
import Header from "./components/Header"
import AboutUs from "./pages/AboutUs"
import ContactUs from "./pages/ContactUs"
import Footer from "./components/Footer"
import Login from "./pages/Login"
import './App.css'
import Register from "./pages/Register"
import AllHouses from "./pages/AllHouses"
import AllTenants from "./pages/AllTenants"
import AllRents from "./pages/AllRents"

function App() {

  return (
    <BrowserRouter>
    <Header />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<AboutUs />} />
        <Route path="/contact" element={<ContactUs />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/all/houses" element={<AllHouses />} />
        <Route path="/house/:houseId/all/tenants" element={<AllTenants />} />
        <Route path="/tenant/:tenantId/all/rents" element={<AllRents />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  )
}

export default App
