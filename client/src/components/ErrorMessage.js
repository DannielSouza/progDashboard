import React from 'react'
import style from "./styles/ErrorMessage.module.css"

const ErrorMessage = ({message}) => {
  return (
    <div className={style.errorContainer}>{message}</div>
  )
}

export default ErrorMessage