import React from 'react'
import emailIcon from './assets/mail.png'
import keyIcon from './assets/key.png'
import userIcon from './assets/user.png'
import { companyRegister } from './helpers/Api'
import { useDispatch } from 'react-redux'
import {saveUser} from '../redux/user/slice'
import ErrorMessage from './ErrorMessage'
import { useNavigate } from "react-router-dom";
import style from "./styles/CompanyRegisterForm.module.css"

const CompanyRegisterForm = ({screen, setScreen}) => {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [userData, setUserData] = React.useState({})
  const [loading, setLoading] = React.useState(false)
  const [error, setError] = React.useState(false)


  function changeUserData({target}){
    setUserData({...userData, [target.name]: target.value})
  }

  async function userRegisterFormSubmit(event){
    event.preventDefault()
    try {
      setError(false)
      setLoading(true)
      const response = await companyRegister(userData)
      localStorage.setItem("progMindAuth", JSON.stringify(response.token))
      dispatch(saveUser(response))
      navigate("/company")
    } catch (error) {
      setError(error.response.data.error)
    }finally{
      setLoading(false)
    }
  }


  return (
    <form onSubmit={userRegisterFormSubmit} className={style.formContainer}>
    {error && <ErrorMessage message={error}/>}

      <h2 className={style.title}>{screen}</h2>

      <label className={style.labelItem}>
          <img src={userIcon} alt="icone de uma pessoa"/>
          <input autoComplete='off' onChange={changeUserData} required name='name' type="text" placeholder='Cadastre o nome da empresa'/>
        </label>

        <label className={style.labelItem}>
          <img src={emailIcon} alt="icone de uma carta"/>
          <input autoComplete='off' onChange={changeUserData} required name='email' type="email" placeholder='Cadastre o e-mail da empresa'/>
        </label>

        <label className={style.labelItem}>
        <img src={keyIcon} alt="icone de uma chave"/>
          <input autoComplete='off' onChange={changeUserData} required name='password' type="password" placeholder='Cadastre uma senha'/>
        </label>

        <p className={style.changeScreen}>Já possui conta? <span onClick={()=>setScreen("Entrar")}>Entrar.</span></p>


        {!loading ?
        <button className={style.sendButton}>Cadastrar</button>
        :
        <button className={style.sendButtonDisabled}>Cadastrar</button>
        }
      </form>
  )
}

export default CompanyRegisterForm