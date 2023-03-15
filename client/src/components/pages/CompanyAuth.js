import React from 'react'
import CompanyLoginForm from '../CompanyLoginForm'
import CompanyRegisterForm from '../CompanyRegisterForm'
import style from './styles/CompanyAuth.module.css'
import logo from '../assets/logo.png'
import { useNavigate } from "react-router-dom";
import autoCheckAuthenticate from '../helpers/AutoAuthenticate'
import { useDispatch } from 'react-redux'
import { saveUser } from '../../redux/user/slice'

const CompanyAuth = () => {

  const [screen, setScreen] = React.useState("Entrar")
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
  },[token])

  return (
    <section>

      <header className={style.header}>
        <img src={logo} alt="Logo do site"/>
        <h2 className={style.title}>Prog<span>Dashboard</span> - Empresa</h2>
      </header>

      {screen === "Entrar" && <CompanyLoginForm screen={screen} setScreen={setScreen}/>}
      {screen === "Cadastrar-se" && <CompanyRegisterForm screen={screen} setScreen={setScreen}/>}

    </section>
  )
}

export default CompanyAuth