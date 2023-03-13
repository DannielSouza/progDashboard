import axios from "axios";

/* LOGIN COMPANY */
async function companyLogin(userData){
  const response = await axios.post("http://localhost:8080/company/login", {
    email: userData.email,
    password: userData.password
  });
  const data = await response.data;

  return data;
}



export { companyLogin };