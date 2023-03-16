import { autoAuthenticate } from "./Api";
import { saveUser } from '../../redux/user/slice'

export default async function checkAutoAuthenticate(dispatch, navigate){

  const token = localStorage.getItem("progDashboardAuth")
  
  if(token){
    const response = await autoAuthenticate(token)

    if(response.role === "ADMIN"){
      dispatch(saveUser(response))
      return navigate("/company")
    } 
    if(response.role === "USER"){
      dispatch(saveUser(response))
      return navigate("/user")
    }
    else{
      return redirectClient(navigate)
    }
  }
  return redirectClient(navigate)
}


function redirectClient(navigate){
  if(window.location.pathname === "/companyAuth"){
    return navigate("/companyAuth")
  }
  if(window.location.pathname === "/userAuth"){
    return navigate("/userAuth")
  }
  return navigate("/")
}