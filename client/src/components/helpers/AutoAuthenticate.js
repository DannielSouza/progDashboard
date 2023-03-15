import { autoAuthenticate } from "./Api";

export default async function checkAutoAuthenticate(token, navigate){
  try {
    const response = await autoAuthenticate(token)
    return response
  } catch (error) {
    navigate("/")
  }
}
