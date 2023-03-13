import React from 'react'
import CompanyLoginForm from '../CompanyLoginForm'
import CompanyRegisterForm from '../CompanyRegisterForm'

const CompanyAuth = () => {

  const [screen, setScreen] = React.useState("LOGIN")

  return (
    <section>

      {screen === "LOGIN" && <CompanyLoginForm setScreen={setScreen}/>}
      {screen === "REGISTER" && <CompanyRegisterForm setScreen={setScreen}/>}

    </section>
  )
}

export default CompanyAuth