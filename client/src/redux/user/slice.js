import { createSlice } from '@reduxjs/toolkit'
const initialState = {
  currentUser: null
}


const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    autoAuthenticationUser: (state, action)=>{
      state.currentUser = action.payload
    },
    login: (state, action)=>{
      state.currentUser = action.payload
    },
    register: (state, action)=>{
      state.currentUser = action.payload
    },
    logout: (state, action)=>{
      state.currentUser = initialState
    }
  }
})

export const { autoAuthenticationUser, login, register, logout } = userSlice.actions

export default userSlice.reducer