import React from 'react'
import style from './styles/LoginHeader.module.css'
import logo from './assets/logo.png'

const LoginHeader = ({page}) => {
  return (
    <header className={style.header}>
      <img src={logo} alt="Logo do site"/>
      <h2 className={style.title}>Prog<span>Dashboard</span> - {page}</h2>
    </header>
  )
}

export default LoginHeader