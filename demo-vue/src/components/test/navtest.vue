<template>
  <nav class="navbar">
    <ul class="navbar__menu">
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="home"></i><span>Home</span></a
        >
      </li>
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="message-square"></i><span>Messages</span></a
        >
      </li>
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="users"></i><span>Customers</span></a
        >
      </li>
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="folder"></i><span>Projects</span></a
        >
      </li>
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="archive"></i><span>Resources</span></a
        >
      </li>
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="help-circle"></i><span>Help</span></a
        >
      </li>
      <li class="navbar__item">
        <a href="#" class="navbar__link"
          ><i data-feather="settings"></i><span>Settings</span></a
        >
      </li>
    </ul>
  </nav>
</template>

<style lang="scss" scoped>
@use "sass:math";
$borderRadius: 10px;
$spacer: 1rem;
$primary: #406ff3;
$text: #6a778e;
$linkHeight: $spacer * 3.5;
$timing: 250ms;
$transition: $timing ease all;

@mixin gooeyEffect($i) {
  @keyframes gooeyEffect-#{$i} {
    0% {
      transform: scale(1, 1);
    }
    50% {
      transform: scale(0.5, 1.5);
    }
    100% {
      transform: scale(1, 1);
    }
  }
}
@import url("https://fonts.googleapis.com/css2?family=Open+Sans:wght@600&display=swap");
body {
  background: #eaeef6;
  font-family: "Open Sans", sans-serif;
}

.navbar {
  $ref: &;
  position: fixed;
  top: $spacer;
  left: $spacer;
  background: rgb(170, 227, 241);
  border-radius: $borderRadius;
  padding: $spacer 0;
  box-shadow: 0 0 40px rgba(0, 0, 0, 0.03);
  height: calc(100vh - #{$spacer * 4});
  &__link {
    position: relative;
    display: flex;
    align-items: center;
    justify-content: center;
    height: $linkHeight;
    width: $spacer * 5.5;
    color: $text;
    transition: $transition;
    span {
      position: absolute;
      left: 100%;
      transform: translate(-($spacer * 3));
      margin-left: 1rem;
      opacity: 0;
      pointer-events: none;
      color: $primary;
      background: #fff;
      padding: $spacer * 0.75;
      transition: $transition;
      border-radius: $borderRadius * 1.75;
    }
    &:hover {
      color: #fff;
    }
    .navbar:not(:hover) &:focus,
    &:hover {
      span {
        opacity: 1;
        transform: translate(0);
      }
    }
  }
  &__menu {
    position: relative;
  }
  &__item {
    &:last-child {
      &:before {
        content: "";
        position: absolute;
        opacity: 0;
        z-index: -1;
        top: 0;
        left: $spacer;
        width: $linkHeight;
        height: $linkHeight;
        background: $primary;
        border-radius: $borderRadius * 1.75;
        transition: $timing cubic-bezier(1, 0.2, 0.1, 1.2) all;
      }
    }

    @for $i from 1 to 12 {
      &:first-child:nth-last-child(#{$i}),
      &:first-child:nth-last-child(#{$i}) ~ li {
        &:hover {
          ~ li:last-child:before {
            opacity: 1;
          }
        }
        &:last-child:hover:before {
          opacity: 1;
        }
        @for $j from 1 to $i {
          &:nth-child(#{$j}):hover {
            ~ li:last-child:before {
              @include gooeyEffect($j);
              top: (100% / $i) * ($j - 1);
              animation: gooeyEffect-#{$j} $timing 1;
            }
          }
        }
        &:last-child:hover:before {
          @include gooeyEffect($i);
          top: (100% / $i) * ($i - 1);
          animation: gooeyEffect-#{$i} $timing 1;
        }
      }
    }
  }
}
</style>

<script lang="ts">
import { defineComponent } from "vue";
export default defineComponent({
  setup() {
    // feather.replace();
    return {};
  },
});
</script>
