import React from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import CompanyAuth from "./components/pages/CompanyAuth";
import CompanyHome from "./components/pages/CompanyHome";
import SelectAccount from "./components/pages/SelectAccount";
import UserAuth from "./components/pages/UserAuth";
import { useSelector } from 'react-redux'
import CompanyHeader from "./components/CompanyHeader";

function App() {
  const { currentUser } = useSelector(rootReducer => rootReducer)
  const [enableCompanyHeader, setEnableCompanyHeader] = React.useState(false)

  React.useEffect(()=>{
    if(currentUser && currentUser.role === "ADMIN") setEnableCompanyHeader(true)
  },[currentUser])

  return (
    <BrowserRouter>
        {enableCompanyHeader && <CompanyHeader />}
      <Routes>
        
        <Route path="/" element={<SelectAccount />} />
        <Route path="/userAuth" element={<UserAuth />} />
        <Route path="/companyAuth" element={<CompanyAuth />} />
        <Route path="/company" element={<CompanyHome />} />
        
      </Routes>
    </BrowserRouter>
  );
}

export default App;