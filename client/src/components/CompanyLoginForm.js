import React from 'react'
import emailIcon from './assets/mail.png'
import keyIcon from './assets/key.png'
import { companyLogin } from './helpers/Api'
import { useDispatch } from 'react-redux'
import {saveUser} from '../redux/user/slice'
import ErrorMessage from './ErrorMessage'
import { useNavigate } from "react-router-dom";
import style from './styles/CompanyLoginForm.module.css'

const CompanyLoginForm = ({screen, setScreen}) => {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [userData, setUserData] = React.useState({})
  const [loading, setLoading] = React.useState(false)
  const [error, setError] = React.useState(false)

  function changeUserData({target}){
    setUserData({...userData, [target.name]: target.value})
  }

  async function userLoginFormSubmit(event){
    event.preventDefault()
    try {
      setError(false)
      setLoading(true)
      const response = await companyLogin(userData)
      localStorage.setItem("progDashboardAuth", JSON.stringify(response.token))
      dispatch(saveUser(response))
      navigate("/company")
    } catch (error) {
      setError(error.response.data.error)
    }finally{
      setLoading(false)
    }
  }

  return (
    <form onSubmit={userLoginFormSubmit} className={style.formContainer}>
      {error && <ErrorMessage message={error}/>}
      <h2 className={style.title}>{screen}</h2>

        <label className={style.labelItem}>
          <img src={emailIcon} alt="icone de carta"/>
          <input autoComplete='off' onChange={changeUserData} type="email" name='email' placeholder='Insira seu e-mail cadastrado' required/>
        </label>

        <label className={style.labelItem}>
        <img src={keyIcon} alt="icone de chave"/>
          <input autoComplete='off' onChange={changeUserData} type="password" name='password' placeholder='Insira sua senha cadastrada' required/>
        </label>

        <p className={style.changeScreen}>Não possui conta? <span onClick={()=>setScreen("Cadastrar-se")}>Registre-se.</span></p>

        {!loading ?
        <button className={style.sendButton}>Entrar</button>
        :
        <button className={style.sendButtonDisabled}>Entrar</button>
        }

      </form>
  )
}

export default CompanyLoginForm