import { autoAuthenticate } from "./Api";

export default async function checkAutoAuthenticate(token, navigate){
  try {
    const response = await autoAuthenticate(token)
    if(response.role){
      navigate("/company")
    }else{
      navigate("/user")
    }
    return response
  } catch (error) {
    navigate("/")
  }
}
