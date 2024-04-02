<template>
  <div>
    <div class="nav-wrap">
      <div class="nav">
        <div>
          <router-link to="/" class="router-link">
            HOME
          </router-link>
        </div>
        <div>
          <router-link to="/coin-price" class="router-link">
            코인 가격 조회
          </router-link>
        </div>
        <div>
          <router-link to="/coin-list" class="router-link">
            코인 리스트 조회
          </router-link>
        </div>
        <div>
          <router-link to="/coin-common" class="router-link">
            공통 코인 조회
          </router-link>
        </div>
      </div>
    </div>

    <router-view></router-view>

  </div>
</template>

<script setup>


</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.data-wrap {
    margin: 5px 0px;
}
.nav-wrap {
  display: flex;
  height: 40px;
  background-color: gray;
  margin-bottom: 30px;
}
.nav {
  display: flex;
  margin: 0px auto;
}
.nav div {
  display: flex;
  text-align: center;
  line-height: 40px;
}
.router-link {
  all: unset;
  color: black;
  font-size: 16px;
  height: 100%;
  padding: 0 10px;
}
.router-link p {
  margin: 0px 10px;
}
.router-link:hover {
  color: white;
  cursor: pointer;
  background-color: #A6A6A6;
}
.btn-nav {
  all: unset;
  color: black;
  font-size: 16px;
  padding: 0px 10px;
}
.coin-list-outer {
    margin: 0 auto;
    width: 150px;
    height: 400px;
    overflow: scroll;
    overflow-x: hidden;
}
.coin-list-wrap {
    margin: 5px;
}
</style>
