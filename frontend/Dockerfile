#STAGE 1
FROM node:16-alpine AS build
WORKDIR /src/app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
# EXPOSE 4200
# CMD ["npm", "start"]

#STAGE 2
FROM nginx:1.17.1-alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=build /src/app/dist/docker-deploy-test /usr/share/nginx/html
