@Override
protected boolean shouldNotFilter(HttpServletRequest request) {

    String path = request.getServletPath();

    return path.startsWith("/swagger-ui")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/users/login")
            || path.startsWith("/users/register")
            || path.startsWith("/parcels");
}
