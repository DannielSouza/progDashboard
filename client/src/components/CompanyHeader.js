import React from 'react'
import { NavLink } from 'react-router-dom'
import style from './styles/CompanyHeader.module.css'
import home from './assets/home.png'
import user from './assets/userHeader.png'
import graph from './assets/graph.png'
import exit from './assets/exit.png'

const CompanyHeader = () => {
  return (
    <header className={style.headerContainer}>
      <div className={style.menuLeft}>
        <NavLink className={style.menuButton} to={"/company/"}>
          <img src={home} alt="Inicio" />
          <span>Inicio</span>
        </NavLink>
        <NavLink className={style.menuButton} to={"/company/users"}>
          <img src={user} alt="Usuários" />
          <span>Usuários</span>
        </NavLink>
        <NavLink className={style.menuButton} to={"/company/dashboard"}>
          <img src={graph} alt="Acompanhamento" />
          <span>Acompanhamento</span>
        </NavLink>
      </div>

      <button className={style.menuButton}>
        <img src={exit} alt="Sair" />
        <span>Sair</span>
      </button>
    </header>
  )
}

export default CompanyHeader