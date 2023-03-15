import { BrowserRouter, Routes, Route } from "react-router-dom";
import CompanyAuth from "./components/pages/CompanyAuth";
import CompanyHome from "./components/pages/CompanyHome";
import SelectAccount from "./components/pages/SelectAccount";
import UserAuth from "./components/pages/UserAuth";

function App() {
  return (
    <BrowserRouter>
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