import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

import "./Login.css";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [token, setToken] = useState(null);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    const loginDto = {
        usernameOrEmail: username,
        password: password,
    }
    console.log('user & pass: ', username, password);

    try {
      const response = await axios.post("http://localhost:8080/auth/login", loginDto);

      if (response.data.token) {
        // Сохраняем токен в localStorage
        localStorage.setItem("token", response.data.token);
        setToken(response.data.token);
        console.log("Login successful");
        // Здесь можно перенаправить пользователя на главную страницу
        navigate('/');
      } else {
        setError("Invalid credentials");
      }
    } catch (error) {
      setError("An error occurred. Please try again.");
      console.error("Error:", error);
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label htmlFor="username">Username:</label>
          <input
            type="text"
            id="username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
        </div>
        <div>
          <label htmlFor="password">Password:</label>
          <input
            type="password"
            id="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <button type="submit" onClick={handleSubmit}>
          Login
        </button>
      </form>
      {error && <p className="error">{error}</p>}
    </div>
  );
};

export default Login;
