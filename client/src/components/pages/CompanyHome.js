import React from 'react'
import { useNavigate } from "react-router-dom";
import autoCheckAuthenticate from '../helpers/AutoAuthenticate'
import { useDispatch } from 'react-redux'
import { saveUser } from '../../redux/user/slice'

const CompanyHome = () => {

  const [token, setToken] = React.useState(localStorage.getItem("progDashboardAuth"))
  const navigate = useNavigate()
  const dispatch = useDispatch()

  React.useEffect(()=>{
    if(token){
      async function autoAuthenticate(){
        const company = await autoCheckAuthenticate(token, navigate)
        dispatch(saveUser(company))
      }
      autoAuthenticate()
    }
    navigate("/")
  },[token])

  return (
    <div>CompanyHome</div>
  )
}

export default CompanyHome