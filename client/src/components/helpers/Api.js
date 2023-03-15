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

/* REGISTER COMPANY */
async function companyRegister(userData){
  const response = await axios.post("http://localhost:8080/company/register", userData);
  const data = await response.data;

  return data;
}

/* AUTO AUTHENTICATE USING TOKEN */
async function autoAuthenticate(token){
  const response = await axios.post("http://localhost:8080/auth", token);
  const data = await response.data;
  return data;
}



export { companyLogin, companyRegister, autoAuthenticate };