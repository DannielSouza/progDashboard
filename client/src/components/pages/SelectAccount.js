import React from 'react'
import style from './styles/SelectAccount.module.css'
import logo from '../assets/logo.png'
import { Link } from 'react-router-dom'
import { useNavigate } from "react-router-dom";
import autoCheckAuthenticate from '../helpers/AutoAuthenticate'
import { useDispatch } from 'react-redux'
import { saveUser } from '../../redux/user/slice'

const SelectAccount = () => {

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