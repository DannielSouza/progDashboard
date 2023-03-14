import React from 'react'
import CompanyLoginForm from '../CompanyLoginForm'
import CompanyRegisterForm from '../CompanyRegisterForm'
import style from './styles/CompanyAuth.module.css'
import logo from '../assets/logo.png'

const CompanyAuth = () => {

  const [screen, setScreen] = React.useState("Entrar")

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