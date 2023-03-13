import React from 'react'
import style from './styles/SelectAccount.module.css'
import logo from '../assets/logo.png'
import { Link } from 'react-router-dom'

const SelectAccount = () => {
  return (
    <section>
      <header className={style.headerContainer}>
        <img src={logo} alt="logo da marca" />

        <div>
          <Link to={"/companyAuth"}>
            <button className={style.headerButton}>Entrar como empresa</button>
          </Link>
          <Link to={"/userAuth"}>
            <button className={style.headerButton}>Entrar como usuário</button>
          </Link>
        </div>
      </header>
    </section>
  )
}

export default SelectAccount