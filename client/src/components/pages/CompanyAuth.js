import React from 'react'
import CompanyLoginForm from '../CompanyLoginForm'
import CompanyRegisterForm from '../CompanyRegisterForm'
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from 'react-redux'
import autoCheckAuthenticate from '../helpers/AutoAuthenticate'
import LoginHeader from '../LoginHeader';


const CompanyAuth = () => {
  const [screen, setScreen] = React.useState("Entrar")
  const { currentUser } = useSelector(rootReducer => rootReducer)
  const navigate = useNavigate()
  const dispatch = useDispatch()

  React.useEffect(()=>{
    if(!currentUser){
      autoCheckAuthenticate(dispatch, navigate)
    }
  },[])

  return (
    <section>
      
      <LoginHeader page={"Empresa"} />

      {screen === "Entrar" && <CompanyLoginForm screen={screen} setScreen={setScreen}/>}
      {screen === "Cadastrar-se" && <CompanyRegisterForm screen={screen} setScreen={setScreen}/>}

    </section>
  )
}

export default CompanyAuth