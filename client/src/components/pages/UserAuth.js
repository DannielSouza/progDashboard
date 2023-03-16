import React from 'react'
import UserLoginForm from '../UserLoginForm'
import LoginHeader from '../LoginHeader'
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux'
import autoCheckAuthenticate from '../helpers/AutoAuthenticate'

const UserAuth = () => {
  const { currentUser } = useSelector(rootReducer => rootReducer)
  const navigate = useNavigate()
  const dispatch = useDispatch()

  React.useEffect(()=>{
    if(!currentUser){
      autoCheckAuthenticate(dispatch, navigate)
    }
  },[])

  return (
    <>
    <LoginHeader page={"Usuário"}/>
    <UserLoginForm />
    </>
  )
}

export default UserAuth