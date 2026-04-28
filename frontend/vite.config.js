import {defineConfig} from 'vite'

export default defineConfig({
    server: {
        proxy: {
            '/quotes': 'http://localhost:8080'
        }
    },
    build: {
        outDir: '../src/main/resources/static',
        emptyOutDir: true
    }
})