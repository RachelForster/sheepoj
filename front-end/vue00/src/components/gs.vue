<template>
  <div class="latexDiv" v-html="'$$'+latex+'$$'" />
</template>

<script>
import MathJax from '@/MathJax.js'
export default {
  name: 'TheLatex2Math',
  props: { latex: { type: String, default: '' }},
  watch: { // 监视latex的变化
    latex() {
      this.mathJax()
    }
  }, // 限制父子组件参数为String
  created() { // 组件刚创建的时候，watch并不会被触发，因为latex值没发生变化
    this.mathJax()
  },
  methods: {
    mathJax () {
      this.$nextTick(function () { // Vue的DOM渲染是异步的
        if (MathJax.isMathjaxConfig) { // 是否配置MathJax
          MathJax.initMathjaxConfig()
        }
        MathJax.MathQueue('latexDiv') // 渲染对应的id/class
      })
    }
  }
}
</script>
