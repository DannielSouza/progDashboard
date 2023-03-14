import { createSlice } from '@reduxjs/toolkit'
const initialState = {
  currentUser: null
}


const userSlice = createSlice({
  name: "user",
  initialState,
  reducers: {
    saveUser: (state, action)=>{
      state.currentUser = action.payload
    },
    logout: (state, action)=>{
      state.currentUser = initialState
    }
  }
})

export const { saveUser, logout } = userSlice.actions

export default userSlice.reducer