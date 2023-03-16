import React from 'react'
import { useNavigate } from "react-router-dom";
import autoCheckAuthenticate from '../helpers/AutoAuthenticate'
import { useDispatch, useSelector } from 'react-redux'


const CompanyHome = () => {
  const { currentUser } = useSelector(rootReducer => rootReducer)
  const navigate = useNavigate()
  const dispatch = useDispatch()

  React.useEffect(()=>{
    if(!currentUser){
      autoCheckAuthenticate(dispatch, navigate)
    }
  },[])

  return (
    <div>CompanyHome</div>
  )
}

export default CompanyHome