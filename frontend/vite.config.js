import {defineConfig} from 'vite'

export default defineConfig({
    server: {
        proxy: {
            '/quotes': 'http://localhost:8080'
        }
    },
    build: {
        outDir: '../src/main/resources/static',
        emptyOutDir: true,
        sourcemap: false,
        minify: 'terser',
        terserOptions: {
            compress: {
                drop_console: true,
                drop_debugger: true,
                passes: 2,
            },
            mangle: {
                toplevel: true,
                properties: true,
            },
            format: {
                comments: false,
                beautify: false,
            },
        },
    },
})