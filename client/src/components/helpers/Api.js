import axios from "axios";

/* LOGIN COMPANY */
async function companyLogin(userData){
  const response = await axios.post("http://localhost:8080/company/login", userData);
  const data = await response.data;

  return data;
}

/* REGISTER COMPANY */
async function companyRegister(userData){
  const response = await axios.post("http://localhost:8080/company/register", userData);
  const data = await response.data;

  return data;
}

/* LOGIN USER */
async function userLogin(userData){
  const response = await axios.post("http://localhost:8080/user/login", userData);
  const data = await response.data;

  return data;
}

/* AUTO AUTHENTICATE USING TOKEN */
async function autoAuthenticate(token){
  const response = await axios.post("http://localhost:8080/auth",null, {
    headers:{
    token: token
    }
  });
  const data = await response.data;
  return data;
}



export { companyLogin, companyRegister, userLogin, autoAuthenticate };